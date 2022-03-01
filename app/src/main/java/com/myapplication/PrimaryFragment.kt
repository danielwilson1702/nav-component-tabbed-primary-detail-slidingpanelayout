package com.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.databinding.FragmentPrimaryBinding
import com.myapplication.databinding.ListItemBinding


class PrimaryFragment : Fragment() {

    private var _binding: FragmentPrimaryBinding? = null
    private val binding get() = _binding!!

    val args by navArgs<PrimaryFragmentArgs>()

    //scoped to the PrimaryDetailHostFragment, each "tab" needs a unique one
    val model: PrimaryViewModel by viewModels({requireParentFragment().requireParentFragment()})

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrimaryBinding.inflate(inflater, container, false)

        binding.primaryText.text = "Primary view, tab number: ${args.tabNumber}"

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = TextItemAdapter(listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10")) { item ->
                model.selection.value = item
            }
        }

        return binding.root
    }

    private class TextItemAdapter(
        private val items: List<String>,
        private val selector: (String) -> Unit
    ) :
        RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
                .let { view ->
                    ViewHolder(
                        ListItemBinding.bind(view)
                    )
                }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
            holder.bind(item) {
                selector(item)
            }
        }

        override fun getItemCount(): Int = items.size
    }

    private class ViewHolder(val itemBinding: ListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(text: CharSequence, onClickListener: View.OnClickListener) {
            itemBinding.text.text = text
            itemBinding.text.setOnClickListener(onClickListener)
        }
    }
}
