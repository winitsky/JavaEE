package dao;

import java.util.List;

public interface InfoByUserIdDAO<T> {
	List<T> getInfoByUserID(int useID);
}
