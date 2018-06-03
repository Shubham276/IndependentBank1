package ten.independentbank;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



public class ListviewAdapter extends BaseAdapter{
    public ArrayList<Model> List;
    Activity activity;

    public ListviewAdapter(Activity activity, ArrayList<Model> productList) {
        super();
        this.activity = activity;
        this.List = productList;
    }

    @Override
    public int getCount() {
        return List.size();
    }

    @Override
    public Object getItem(int position) {
        return List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView title;
        TextView mOrderid;
        TextView mMobile_Number;
        TextView mAmount;
        TextView mstatus;
        TextView mdate;
        ImageView mamtbtn;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_row, null);
            holder = new ViewHolder();
            holder.title= (TextView) convertView.findViewById(R.id.title);
            holder.mOrderid = (TextView) convertView.findViewById(R.id.Orderid);
            holder.mMobile_Number = (TextView) convertView.findViewById(R.id.mobile_Number);
            holder.mAmount = (TextView) convertView
                    .findViewById(R.id.amount);
            holder.mstatus = (TextView) convertView.findViewById(R.id.status);
            holder.mdate = (TextView) convertView.findViewById(R.id.date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Model item = List.get(position);
        holder.mOrderid.setText(item.getOrderid().toString());
        holder.mMobile_Number.setText(item.getMobile_Number().toString());
        holder.mAmount.setText(item.getAmount().toString());
        holder.mstatus.setText(item.getStatus().toString());
        holder.mdate.setText(item.getDate().toString());
        return convertView;
    }
}
