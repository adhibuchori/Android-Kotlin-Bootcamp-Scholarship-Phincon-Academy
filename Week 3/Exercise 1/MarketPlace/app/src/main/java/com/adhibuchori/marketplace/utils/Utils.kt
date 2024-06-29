package com.adhibuchori.marketplace.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.adhibuchori.marketplace.databinding.ToolbarBinding

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>(private val bindingFactory: (LayoutInflater, ViewGroup?, Boolean) -> VB) :
    Fragment() {

    protected lateinit var binding: VB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = bindingFactory.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    open fun setupViews() {

    }
}

abstract class BaseRecyclerViewAdapter<T, VB : ViewBinding>(
    private val itemList: List<T>,
    private val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB
) : RecyclerView.Adapter<BaseRecyclerViewAdapter<T, VB>.BaseViewHolder>() {

    private var onItemClickListener: ((T) -> Unit)? = null

    abstract fun bindViews(binding: VB, data: T)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = bindingInflater(inflater, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun setOnItemClickListener(listener: (T) -> Unit) {
        onItemClickListener = listener
    }

    inner class BaseViewHolder(val binding: VB) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: T) {
            bindViews(binding, data)
            itemView.setOnClickListener {
                onItemClickListener?.invoke(data)
            }
        }
    }
}

interface ToolbarSetup {
    fun setUp(
        toolbarBinding: ToolbarBinding,
        context: Context,
        title: String,
        imageDrawable: Drawable? = null
    )
}

class ToolbarManager : ToolbarSetup {
    override fun setUp(
        toolbarBinding: ToolbarBinding,
        context: Context,
        title: String,
        imageDrawable: Drawable?
    ) {
        with(toolbarBinding) {
            val layoutParams =
                tvToolbarTitle.layoutParams as androidx.appcompat.widget.Toolbar.LayoutParams
            layoutParams.gravity = Gravity.CENTER
            tvToolbarTitle.layoutParams = layoutParams

            tvToolbarTitle.text = title
            imageDrawable?.let { ivToolbarProfile.setImageDrawable(it) }
            ivToolbarProfile.gone()
            ivToolbarNotification.gone()
            ivToolbarShoppingCart.gone()
            ivToolbarMenu.gone()
        }
    }
}

class TitleOnlyToolbarManager : ToolbarSetup {
    override fun setUp(
        toolbarBinding: ToolbarBinding,
        context: Context,
        title: String,
        imageDrawable: Drawable?
    ) {
        with(toolbarBinding) {
            tvToolbarTitle.text = title
        }
    }
}

class TitleAndIconToolbarManager : ToolbarSetup {
    override fun setUp(
        toolbarBinding: ToolbarBinding,
        context: Context,
        title: String,
        imageDrawable: Drawable?
    ) {
        with(toolbarBinding) {
            tvToolbarTitle.text = title
            imageDrawable?.let { ivToolbarProfile.setImageDrawable(it) }
            ivToolbarNotification.gone()
            ivToolbarShoppingCart.gone()
            ivToolbarMenu.gone()
        }
    }
}

fun setupToolbar(
    toolbarBinding: ToolbarBinding,
    context: Context,
    title: String,
    imageDrawable: Drawable? = null,
    manager: ToolbarSetup
) {
    manager.setUp(toolbarBinding, context, title, imageDrawable)
}