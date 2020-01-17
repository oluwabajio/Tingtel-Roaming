package tingtel.android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import tingtel.android.R;
import tingtel.android.models.Balance;

public class BalanceAdapter extends RecyclerView.Adapter<BalanceAdapter.MyViewHolder> {
    private final Context mContext;
    private final List<Balance> mData;

    public BalanceAdapter(Context mContext, List lst) {

        this.mContext = mContext;
        this.mData = lst;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.view_balance, parent, false);
        // click listener here
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.itemView.setTag(mData.get(position));

        holder.tvAmount.setText("#" + mData.get(position).getSimUuid());
        holder.tvAmount.setText("");
        holder.tvDate.setText(new SimpleDateFormat("MMMM dd, hh:mm a").format(mData.get(position).getDate()));
        holder.tvServiceName.setText(mData.get(position).getType());
        holder.tvTitle.setText(mData.get(position).getMessage());
        holder.tvSim.setText(mData.get(position).getSimName());

//        setNetworkImage(holder, mData.get(position).getSimName());
    }




    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        final TextView tvDate;
        final TextView tvAmount;
        final TextView tvTitle;
        final TextView tvSim;
        final TextView tvServiceName;
        final ImageView imgLogo;


        MyViewHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvAmount = itemView.findViewById(R.id.tv_amount);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvSim = itemView.findViewById(R.id.tv_sim);
            tvServiceName = itemView.findViewById(R.id.tv_serviceName);

            imgLogo = itemView.findViewById(R.id.img_logo);

            Context context = itemView.getContext();

            itemView.setOnClickListener(view -> {
                Balance BalanceModel = (Balance) view.getTag();
//                    Intent i = new Intent(view.getContext(), MainActivity.class);
//                    i.putExtra("desc", cpu.getCode());
//                    i.putExtra("title", cpu.getName());
//                    view.getContext().startActivity(i);

                // method.DialUssdCode((BanksBalancesActivity)context, BalanceModel.get, context, 0);
            });
            itemView.setOnLongClickListener(v -> true);
        }
    }


}
