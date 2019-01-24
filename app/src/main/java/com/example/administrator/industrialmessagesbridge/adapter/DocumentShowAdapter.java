package com.example.administrator.industrialmessagesbridge.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.model.FileInfo;

import java.util.List;

import butterknife.OnClick;

public class DocumentShowAdapter extends RecyclerView.Adapter {
    private List<FileInfo>fileInfoList;
  public class DocumentShowViewHolder extends RecyclerView.ViewHolder{
     public  TextView document_name;
     public TextView document_time;
     public TextView ducument_price;
     public LinearLayout document_show_ll;
      public DocumentShowViewHolder(View itemView) {
          super(itemView);
          document_name=(TextView)itemView.findViewById(R.id.document_name);
          document_time=(TextView)itemView.findViewById(R.id.document_time);
          ducument_price=(TextView)itemView.findViewById(R.id.ducument_price);
          document_show_ll=(LinearLayout)itemView.findViewById(R.id.document_show_ll);
      }
  }
    public DocumentShowAdapter(List<FileInfo> fileInfoList) {
        this.fileInfoList = fileInfoList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DocumentShowViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.document_show_list,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DocumentShowViewHolder documentShowViewHolder=(DocumentShowViewHolder)holder;
        documentShowViewHolder.document_show_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onclick(position);
            }
        });
        documentShowViewHolder.document_time.setText(fileInfoList.get(position).getFilesUploadTime());
        documentShowViewHolder.document_name.setText(fileInfoList.get(position).getFilesName());
        documentShowViewHolder.ducument_price.setText(fileInfoList.get(position).getFilesPrice());
    }
public interface Onclick{
      public void onclick(int position);
}
private Onclick onClick;

    public void setOnClick(Onclick onClick) {
        this.onClick = onClick;
    }

    @Override
    public int getItemCount() {
        return fileInfoList.size();
    }
}
