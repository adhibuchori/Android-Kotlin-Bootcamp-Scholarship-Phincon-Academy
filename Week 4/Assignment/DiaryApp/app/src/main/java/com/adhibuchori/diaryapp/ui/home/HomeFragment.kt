package com.adhibuchori.diaryapp.ui.home

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhibuchori.diaryapp.R
import com.adhibuchori.diaryapp.data.database.DiaryData
import com.adhibuchori.diaryapp.databinding.FragmentHomeBinding
import com.adhibuchori.diaryapp.databinding.ItemDialogConfirmationBinding
import com.adhibuchori.diaryapp.databinding.ItemDialogCustomizeTaskBinding
import com.adhibuchori.diaryapp.ui.home.adapter.ListItemDiaryAdapter
import com.adhibuchori.diaryapp.utils.BaseFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HomeFragment : BaseFragment<FragmentHomeBinding, ViewModel>(FragmentHomeBinding::inflate) {

    private var diaryData: DiaryData? = null

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var diaryAdapter: ListItemDiaryAdapter

    override fun setupViews() {
        setupListener()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        homeViewModel.getAllDiaries().observe(viewLifecycleOwner) { diaryDataList ->
            println("Mengecek $diaryDataList")
            diaryAdapter.setListDiaryData(diaryDataList)
        }

        diaryAdapter = ListItemDiaryAdapter()

        with(binding.rvHomePage) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = diaryAdapter

            diaryAdapter.setOnItemClickCallback(object : ListItemDiaryAdapter.OnItemClickCallback {
                override fun onItemClicked(diaryData: DiaryData) {
                    setupEditTaskDialog(diaryData)
                }

                override fun onItemChecked(diaryData: DiaryData, checked: Boolean) {
                    homeViewModel.updateChecked(diaryData.copy(isChecked = checked))
                }

                override fun onItemDeleted(diaryData: DiaryData) {
                    setupDeleteTaskDialog(diaryData)
                }
            })
        }
    }

    private fun setupListener() {
        binding.fabHomePageAddTask.setOnClickListener { setupAddTaskDialog() }
    }

    private fun setupAddTaskDialog() {
        val dialogBinding = ItemDialogCustomizeTaskBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(requireActivity())
            .setView(dialogBinding.root)
            .setCancelable(true)

        val dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialog.show()

        val layoutParams = dialogBinding.root.layoutParams as ViewGroup.MarginLayoutParams
        val horizontalMarginInDp = 16
        val horizontalMarginInPx = (horizontalMarginInDp * resources.displayMetrics.density).toInt()
        layoutParams.marginStart = horizontalMarginInPx
        layoutParams.marginEnd = horizontalMarginInPx
        dialogBinding.root.layoutParams = layoutParams

        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        with(dialogBinding) {
            ivDialogCustomizeTaskCloseIcon.setOnClickListener { dialog.dismiss() }
            tietDialogCustomizeTaskDiaryDueDate.setOnClickListener {
                showDatePickerDialog(
                    dialogBinding
                )
            }
            tvDialogCustomizeTaskTitle.setText(R.string.add_task_title)
            btnDialogCustomizeTask.setText(R.string.add_task_title)

            btnDialogCustomizeTask.setOnClickListener {
                val title = tietDialogCustomizeTaskDiaryTitle.text.toString().trim()
                val description = tietDialogCustomizeTaskDiaryDescription.text.toString().trim()
                val dueDate = tietDialogCustomizeTaskDiaryDueDate.text.toString().trim()
                when {
                    title.isEmpty() -> {
                        tietDialogCustomizeTaskDiaryTitle.error = getString(R.string.empty)
                    }

                    description.isEmpty() -> {
                        tietDialogCustomizeTaskDiaryDescription.error = getString(R.string.empty)
                    }

                    else -> {
                        diaryData = DiaryData(
                            title = title,
                            description = description,
                            date = dueDate
                        )
                        diaryData?.let { diary -> homeViewModel.insert(diary) }
                        showToast(getString(R.string.added))
                    }
                }
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    private fun setupEditTaskDialog(diaryData: DiaryData) {
        val dialogBinding = ItemDialogCustomizeTaskBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setCancelable(true)

        val dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialog.show()

        val layoutParams = dialogBinding.root.layoutParams as ViewGroup.MarginLayoutParams
        val horizontalMarginInDp = 16
        val horizontalMarginInPx = (horizontalMarginInDp * resources.displayMetrics.density).toInt()
        layoutParams.marginStart = horizontalMarginInPx
        layoutParams.marginEnd = horizontalMarginInPx
        dialogBinding.root.layoutParams = layoutParams

        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        with(dialogBinding) {
            ivDialogCustomizeTaskCloseIcon.setOnClickListener { dialog.dismiss() }
            tietDialogCustomizeTaskDiaryDueDate.setOnClickListener {
                showDatePickerDialog(dialogBinding)
            }
            tvDialogCustomizeTaskTitle.setText(R.string.edit_task_title)
            btnDialogCustomizeTask.setText(R.string.edit_task_title)

            tietDialogCustomizeTaskDiaryTitle.setText(diaryData.title)
            tietDialogCustomizeTaskDiaryDescription.setText(diaryData.description)
            tietDialogCustomizeTaskDiaryDueDate.setText(diaryData.date)

            btnDialogCustomizeTask.setOnClickListener {
                val title = tietDialogCustomizeTaskDiaryTitle.text.toString().trim()
                val description = tietDialogCustomizeTaskDiaryDescription.text.toString().trim()
                val dueDate = tietDialogCustomizeTaskDiaryDueDate.text.toString().trim()
                when {
                    title.isEmpty() -> {
                        tietDialogCustomizeTaskDiaryTitle.error = getString(R.string.empty)
                    }

                    description.isEmpty() -> {
                        tietDialogCustomizeTaskDiaryDescription.error = getString(R.string.empty)
                    }

                    else -> {
                        val newData = diaryData.copy(
                            title = title,
                            description = description,
                            date = dueDate
                        )
                        homeViewModel.update(newData)
                    }
                }
                dialog.dismiss()
            }
        }
    }

    private fun setupDeleteTaskDialog(diaryData: DiaryData) {
        val dialogBinding = ItemDialogConfirmationBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(requireActivity())
            .setView(dialogBinding.root)
            .setCancelable(true)

        val dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialog.show()

        val layoutParams = dialogBinding.root.layoutParams as ViewGroup.MarginLayoutParams
        val horizontalMarginInDp = 50
        val horizontalMarginInPx = (horizontalMarginInDp * resources.displayMetrics.density).toInt()
        layoutParams.marginStart = horizontalMarginInPx
        layoutParams.marginEnd = horizontalMarginInPx
        dialogBinding.root.layoutParams = layoutParams

        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        with(dialogBinding) {
            ivItemDialogConfirmation.setImageDrawable(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.iv_delete_task
                )
            )
            btnItemDialogConfirmationNo.setOnClickListener {
                dialog.dismiss()
            }
            btnItemDialogConfirmationYes.setOnClickListener {
                homeViewModel.delete(diaryData)
                dialog.dismiss()
            }
        }
    }

    private fun showDatePickerDialog(dialogBinding: ItemDialogCustomizeTaskBinding) {
        val editText = dialogBinding.tietDialogCustomizeTaskDiaryDueDate

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog =
            DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, selectedDay)

                val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)

                editText.setText(formattedDate)
            }, year, month, day)

        datePickerDialog.show()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }
}