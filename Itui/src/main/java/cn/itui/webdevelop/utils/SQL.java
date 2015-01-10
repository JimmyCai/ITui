package cn.itui.webdevelop.utils;

public class SQL {
	public static final String GETCOLLEGELOGOANDRANKINFOBYMAJORID = "SELECT college.logo,college.rank,college.local_rank FROM college,major WHERE major.id=:MajorId AND college.id=major.college_id";
	
	public static final String GETMAJORINFOBYID = "FROM MajorInfo WHERE id=:MajorId";
	
	public static final String GETMAJORBYID = "FROM Major WHERE id=:MajorId";
	
	public static final String GETNSCOREBYMAJORID = "SELECT year, score FROM score WHERE major_id=:MajorId ORDER BY year DESC LIMIT 0,:Count";
}
