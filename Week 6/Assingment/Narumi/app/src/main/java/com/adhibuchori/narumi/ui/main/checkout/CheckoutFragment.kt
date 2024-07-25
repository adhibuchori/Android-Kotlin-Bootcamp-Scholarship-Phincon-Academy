package com.adhibuchori.narumi.ui.main.checkout

import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.WindowCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhibuchori.narumi.R
import com.adhibuchori.narumi.data.checkout.PaymentItem
import com.adhibuchori.narumi.databinding.FragmentCheckoutBinding
import com.adhibuchori.narumi.databinding.ItemBottomSheetPaymentMethodBinding
import com.adhibuchori.narumi.ui.main.checkout.adapter.PaymentMethodAdapter
import com.adhibuchori.narumi.utils.base.BaseFragment
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope
import java.text.NumberFormat
import java.util.Locale

class CheckoutFragment :
    BaseFragment<FragmentCheckoutBinding, ViewModel>(FragmentCheckoutBinding::inflate),
    AndroidScopeComponent {

    override val scope: Scope by fragmentScope()
    private val checkoutViewModel: CheckoutViewModel by viewModel()
    private val paymentViewModel: PaymentViewModel by viewModels()

    private lateinit var virtualBankAdapter: PaymentMethodAdapter
    private lateinit var transferBankAdapter: PaymentMethodAdapter
    private lateinit var instantPaymentAdapter: PaymentMethodAdapter

    private val args: CheckoutFragmentArgs by navArgs()

    private var bottomSheetDialog: BottomSheetDialog? = null
    private lateinit var bottomSheetBinding: ItemBottomSheetPaymentMethodBinding

    private lateinit var navController: NavController

    override fun setupViews() {
        navController = findNavController()
        retrieveData()
        initView()
        setupStatusBar()
        setupNavigation()
        setupToolbar()
        setupListener()
        setupPaymentMethod()
        listenData()
    }

    private fun initView() = with(binding) {
        checkoutViewModel.fetchTripById(args.checkoutData)

        virtualBankAdapter = PaymentMethodAdapter()
        transferBankAdapter = PaymentMethodAdapter()
        instantPaymentAdapter = PaymentMethodAdapter()

        bottomSheetDialog = context?.let { BottomSheetDialog(it) }
        bottomSheetBinding = ItemBottomSheetPaymentMethodBinding.inflate(layoutInflater)

        checkoutViewModel.tripData.observe(viewLifecycleOwner) {
            ivCheckoutPageTripImage.setImageResource(it.tripImage)
            tvCheckoutPageTripName.text = it.tripName
            tvCheckoutPageTripLocation.text = it.tripLocation
            tvCheckoutPageTripNameDetail.text = it.tripName
            tvCheckoutPageTripLocationDetail.text = it.tripLocation
            tvCheckoutPageTripDateDetail.text = it.tripDate
            tvCheckoutPageTripDurationDetail.text = it.tripDuration
            tvCheckoutPageTripCost.text = formatCurrency(it.tripCost)
        }
    }

    private fun setupListener() {
        with(binding) {
            ivCheckoutPageAdditionIcon.setOnClickListener { checkoutViewModel.increaseItemPax() }
            ivCheckoutPageSubtractionIcon.setOnClickListener { checkoutViewModel.decreaseItemPax() }
            btnCheckoutPageBookNow.setOnClickListener {
                checkoutViewModel.checkout()
                Toast.makeText(context, "Order Trip Successful", Toast.LENGTH_SHORT).show()
                navigateToHome()
            }
        }
    }

    private fun listenData() {
        with(checkoutViewModel) {
            itemPax.observe(viewLifecycleOwner) {
                val totalCostFormatted = formatCurrency(countTotalCost(it))

                with(binding) {
                    tvCheckoutPageTripCount.text = it.toString()
                    tvCheckoutPageTripCost.text = totalCostFormatted
                }
            }
        }
    }

    private fun setupPaymentMethod() {
        virtualBankAdapter.setOnItemClickListener {
            bottomSheetDialog?.dismiss()
            selectPayment(it)
        }
        transferBankAdapter.setOnItemClickListener {
            bottomSheetDialog?.dismiss()
            selectPayment(it)
        }
        instantPaymentAdapter.setOnItemClickListener {
            bottomSheetDialog?.dismiss()
            selectPayment(it)
        }
    }

    private fun selectPayment(item: PaymentItem) {
        with(binding) {
            binding.tvCheckoutPageChoosePaymentMethod.text = item.label
            ivCheckoutPagePaymentMethodIcon.apply {
                val lp = layoutParams as ConstraintLayout.LayoutParams
                lp.apply {
                    width = resources.getDimensionPixelSize(R.dimen.payment_method_icon_width)
                    height = resources.getDimensionPixelSize(R.dimen.payment_method_icon_height)
                }
                layoutParams = lp
            }
            Glide.with(ivCheckoutPageTripImage.context)
                .load(item.image)
                .into(ivCheckoutPagePaymentMethodIcon)
        }
    }

    private fun retrieveData() {
        with(paymentViewModel) {
            getPaymentMethodList()
            paymentList.observe(viewLifecycleOwner) { it ->
                val virtualAccount = it.find { it.title == "Transfer Virtual Account" }
                val transferBank = it.find { it.title == "Transfer Bank" }
                val instantPayment = it.find { it.title == "Pembayaran Instan" }

                virtualAccount?.item?.let { item -> virtualBankAdapter.setData(item) }
                transferBank?.item?.let { item -> transferBankAdapter.setData(item) }
                instantPayment?.item?.let { item -> instantPaymentAdapter.setData(item) }
            }
        }
    }

    private fun setupStatusBar() {
        activity?.window?.let {
            WindowCompat.setDecorFitsSystemWindows(
                it,
                true
            )
        }
    }

    private fun setupNavigation() {
        with(binding) {
            cvCheckoutPageChoosePaymentMethodContainer.setOnClickListener { showPaymentMethodBottomSheet() }
        }
    }

    private fun showPaymentMethodBottomSheet() {
        bottomSheetDialog?.setContentView(bottomSheetBinding.root)

        bottomSheetBinding.ivBottomSheetArrowBackIcon.setOnClickListener {
            bottomSheetDialog?.dismiss()
        }

        setupPaymentMethodsRecyclerView(bottomSheetBinding)
        bottomSheetDialog?.show()
    }

    private fun setupPaymentMethodsRecyclerView(bottomSheetBinding: ItemBottomSheetPaymentMethodBinding) {
        with(bottomSheetBinding) {
            rvBottomSheetVirtualAccount.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = virtualBankAdapter
            }
            rvBottomSheetTransferBank.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = transferBankAdapter
            }
            rvBottomSheetInstantPayment.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = instantPaymentAdapter
            }
        }

    }

    private fun setupToolbar() {
        with(binding.checkoutPageToolbar) {
            tvToolbarTitle.text = getString(R.string.checkout)
            ivToolbarArrowBackIcon.setOnClickListener {
                activity?.onBackPressedDispatcher?.onBackPressed()
            }
        }
    }

    private fun navigateToHome() {
        val action = CheckoutFragmentDirections.actionCheckoutFragmentToMainFragment()
        navController.navigate(action)
    }

    private fun formatCurrency(amount: Int): String {
        val format = NumberFormat.getCurrencyInstance(Locale("en", "US"))
        format.maximumFractionDigits = 0
        return format.format(amount)
    }
}