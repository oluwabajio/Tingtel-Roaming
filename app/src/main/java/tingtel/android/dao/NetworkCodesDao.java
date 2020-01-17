package tingtel.android.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import tingtel.android.models.NetworksCode;

@Dao
public interface NetworkCodesDao {
    @Query("SELECT * FROM NetworksCode")
    List<NetworksCode> getAllItems();

    @Insert
    void insertAll(NetworksCode... NetworksCode);

    @Query("DELETE FROM NetworksCode")
    void delete();

    @Query("SELECT * FROM NetworksCode WHERE name = :name")
    List<NetworksCode> getItemsByName(String name);
}
