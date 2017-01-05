package DAO;

import java.util.List;

public interface CountInfoDAO {
	public List getAllCountInfo();
	public double getCountByTag(String tag);
}
