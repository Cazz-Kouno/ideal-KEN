package model;

import java.util.Objects;

public class IdealException extends Exception {
    private int errCd;
    private static final String[] ERR_MSG = {
            "障害が発生しました。",
            "データベース処理で例外が発生しました。",
            "お客様ID、パスワードを確認してください。",
            "管理者名、パスワードを確認してください。",
            "ご指定された日時に、空席がございませんでした。",
            "予約されているコースなので削除できません。",
            "コースに登録されているメニューなので削除できません."
    };

    public static final int ERR_NO_EXCEPTION = 0;
    public static final int ERR_NO_DB_EXCEPTION = 1;
    public static final int ERR_NO_NOT_MEMBER_EXCEPTION = 2;
    public static final int ERR_NO_ADMIN_EXCEPTION = 3;
    public static final int ERR_NO_NOT_VACANCY = 4;
    public static final int ERR_NO_NOT_RESERV_DELETE = 5;
    public static final int ERR_NO_NOT_MENU_DELETE = 6;

    public IdealException(int errCd) {
        super(ERR_MSG[errCd]);
        this.errCd = errCd;
    }

    public int getErrCd() {
        return errCd;
    }

    public String getMsg() {
        return ERR_MSG[errCd];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdealException that = (IdealException) o;
        return errCd == that.errCd;
    }

    @Override
    public int hashCode() {
        return Objects.hash(errCd);
    }

    @Override
    public String toString() {
        return "IdealException{" +
                "errCd=" + errCd +
                ", errMsg='" + ERR_MSG[errCd] + '\'' +
                '}';
    }
}