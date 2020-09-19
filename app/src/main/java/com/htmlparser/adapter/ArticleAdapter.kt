package com.htmlparser.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.htmlparser.databinding.*
import com.htmlparser.elements.*

class ArticleAdapter :
    ListAdapter<Element, RecyclerView.ViewHolder>(ArticleDiffCallback()) {

    private val TAG = ArticleAdapter::class.java.simpleName
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val article = getItem(position)
        if (holder is FigureItemViewHolder)
            holder.bind(article as FigureElement)
        else if (holder is EmptyItemViewHolder)
            holder.bind()
        else if (holder is ParagraphItemViewHolder) {
            holder.bind(article as ParagraphElement)
        } else if (holder is IFrameItemViewHolder)
            holder.bind(article as IFrameElement)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return when (viewType) {
            ElementType.Figure.ordinal -> {
                return FigureItemViewHolder(FigureItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }

            ElementType.Paragraph.ordinal  -> {
                return ParagraphItemViewHolder(ParagraphItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }

            ElementType.IFrame.ordinal -> {
                return IFrameItemViewHolder(IframeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
//
//            ElementType.Image.ordinal -> {
//
//            }

            else -> {
                return EmptyItemViewHolder(EmptyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return currentList.get(position).type
    }


    inner class FigureItemViewHolder(private val binding: FigureItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(figureElement: FigureElement) {
            binding.apply {
                figure = figureElement
                executePendingBindings()
            }
        }
    }

    inner class EmptyItemViewHolder(private val binding: EmptyItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind () {

        }
    }

    inner class ParagraphItemViewHolder(private val binding: ParagraphItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(paragraphElement: ParagraphElement) {
            binding.apply {
                paragraph = paragraphElement
                executePendingBindings()
            }
        }
    }


    inner class IFrameItemViewHolder(private val binding: IframeItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(iframeContent: IFrameElement) {
            binding.apply {
                content = iframeContent
                executePendingBindings()
            }
        }
    }



}

private class ArticleDiffCallback : DiffUtil.ItemCallback<Element>() {

    override fun areItemsTheSame(oldItem: Element, newItem: Element): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Element, newItem: Element): Boolean {
        return oldItem == newItem
    }
}