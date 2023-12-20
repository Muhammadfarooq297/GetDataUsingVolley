import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.getdatausingvolley.R
import com.example.getdatausingvolley.UserInfoList

class Adapter(val context: Context, val userInfo: UserInfoList) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.single_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(userInfo[position].avatar_url).into(holder.userImage)
        holder.userName.text = userInfo[position].login
    }

    override fun getItemCount(): Int {
        return userInfo.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userImage: ImageView = itemView.findViewById(R.id.imageView)
        val userName: TextView = itemView.findViewById(R.id.textView)
    }
}
