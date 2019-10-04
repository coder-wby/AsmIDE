package com.word.asmide.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.word.asmide.R;

import java.util.List;

public class WRecyclerAdapter extends RecyclerView.Adapter<WRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<String> items;
    public static String PARENT_DIR = "./";
    private OnItemClickListener itemClickListener;

    public WRecyclerAdapter(Context context, List<String> items) {
        this.context = context;
        this.items = items;
    }

    /***
     * 更新列表数据并重绘
      * @param items 新的数据
     */
    public void update(List<String> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    /***
     *
     * @param parent
     * @param viewType
     * @return ViewHolder
     * 用于创建ViewHolder
     * 将xml布局文件inflate成View对象
     * 传送给ViewHolder
     * 最后返回这个ViewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.main_drawer_list_item,parent,false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String tv = items.get(position);
        holder.item_TextView.setText(tv);
        //判断其是否为空，若不为空，则为其设置点击监听
        if(itemClickListener != null) {

            holder.root.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    itemClickListener.OnItemClick(position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    //这个ViewHolder是用来初始化控件的
    class ViewHolder extends RecyclerView.ViewHolder {
        ViewGroup root;
        TextView item_TextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.mDrawer_list_item_root);
            item_TextView = itemView.findViewById(R.id.mDrawer_list_item_text);

        }
    }
}
