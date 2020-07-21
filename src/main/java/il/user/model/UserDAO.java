package il.user.model;

public interface UserDAO {
   
   public int userJoin(UserDTO dto);
   
   public int idCheck(String id);
   public int nicknameCheck(String nickname);
   public String loginCheck(String userid);
   public String getUserInfo(String userid);
}