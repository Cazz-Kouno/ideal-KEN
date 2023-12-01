package model.test;

import java.sql.SQLException;

import model.IdealException;
import model.User;

public class UserTest {
    public static void main(String[] args) throws SQLException {
        try {
            // ログイン処理のテスト
            testLogin();
            
            // 顧客情報取得処理のテスト
            testGetUser();

            // 顧客情報登録処理のテスト
            testInsert();

            // 顧客情報変更処理のテスト
            testUpdate();
            
            // 顧客情報削除処理のテスト
            testDelete();
            
        } catch (IdealException e) {
            e.printStackTrace();
        }
    }

    private static void testLogin() throws IdealException {
        User user = new User();
        int usrId = 1; // テスト用の顧客ID
        String password = "*94BDCEBE19083CE2A1F959FD02F964C7AF4CFC29"; // テスト用のパスワード

        User loggedInUser = user.login(usrId, password);

        if (loggedInUser != null) {
            System.out.println("ログイン成功");
            System.out.println("顧客ID: " + loggedInUser.getUsrId());
            System.out.println("氏名: " + loggedInUser.getUsrName());
            System.out.println("パスワード: " + loggedInUser.getPassword());
            System.out.println("住所: " + loggedInUser.getAddress());
            System.out.println("電話番号: " + loggedInUser.getPhone());
            System.out.println("アドレス: " + loggedInUser.getMail());
            System.out.println("備考: " + loggedInUser.getExp());
        } else {
            System.out.println("ログイン失敗");
        }
        System.out.println("---------------");
    	}
        private static void testGetUser() throws IdealException {
            User user = new User();
            int usrId = 1; // テスト用の顧客ID

            User retrievedUser = user.getUser(usrId);

            if (retrievedUser != null) {
                System.out.println("顧客情報取得成功");
                System.out.println("顧客ID: " + retrievedUser.getUsrId());
                System.out.println("氏名: " + retrievedUser.getUsrName());
                System.out.println("パスワード: " + retrievedUser.getPassword());
                System.out.println("住所: " + retrievedUser.getAddress());
                System.out.println("電話番号: " + retrievedUser.getPhone());
                System.out.println("アドレス: " + retrievedUser.getMail());
                System.out.println("備考: " + retrievedUser.getExp());
            } else {
                System.out.println("顧客情報取得失敗");
            }
            System.out.println("---------------");
        }
        private static void testInsert() throws IdealException {
            User user = new User();
            // テスト用の顧客情報
            User newUser = new User();
            newUser.setUsrName("Test User");
            newUser.setPassword("test123");
            newUser.setAddress("Test Address");
            newUser.setPhone("123-456-7890");
            newUser.setMail("test@example.com");
            newUser.setExp("Test User");

            User insertedUser = user.insert(newUser);

            if (insertedUser != null) {
                System.out.println("顧客情報登録成功");
                System.out.println("登録した顧客の顧客ID: " + insertedUser.getUsrId());
            } else {
                System.out.println("顧客情報登録失敗");
            }
            System.out.println("---------------");
        }

        private static void testUpdate() throws IdealException {
            User user = new User();
            // テスト用の顧客情報
            User updateUser = new User();
            updateUser.setUsrId(1); // 更新対象の顧客ID
            updateUser.setUsrName("Updated User");
            updateUser.setPassword("updated123");
            updateUser.setAddress("Updated Address");
            updateUser.setPhone("987-654-3210");
            updateUser.setMail("updated@example.com");
            updateUser.setExp("Updated User");

            User updatedUser = user.update(updateUser);

            if (updatedUser != null) {
                System.out.println("顧客情報変更成功");
                System.out.println("変更後の顧客ID: " + updatedUser.getUsrId());
            } else {
                System.out.println("顧客情報変更失敗");
            }
            System.out.println("---------------");
        }
            
            private static void testDelete() throws IdealException, SQLException {
                User user = new User();
                // テスト用の顧客情報
                User deleteUser = new User();
                deleteUser.setUsrId(1); // 削除対象の顧客ID

                user.delete(deleteUser);
                
                System.out.println("顧客情報削除成功");
                System.out.println("---------------");
        }
    }