package il.user.model;

import org.mybatis.spring.SqlSessionTemplate;

public class UserDAOImple implements UserDAO {
   
   private SqlSessionTemplate sst;
   
   public UserDAOImple(SqlSessionTemplate sst) {
      super();
      this.sst = sst;
   }
   public int userJoin(UserDTO dto) {
      int count = sst.insert("userInsert",dto);
      
      return count;
   }

   public int idCheck(String id) {
     
     int count = sst.selectOne("idCheck",id);
     return count==0?0:count;
   }
   
   public int nicknameCheck(String nickname) {
      
      int count = sst.selectOne("nicknameCheck",nickname);
      return count==0?0:count;
   }
   
   public String loginCheck(String userid) {
	   String pwd = sst.selectOne("loginCheck", userid);
	   return pwd;
   }
   
   public String getUserInfo(String userid) {
	   String name = sst.selectOne("getUserInfo", userid);
	   return name;
   }
}