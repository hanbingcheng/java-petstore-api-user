package com.example.petstore.api.user.domain.constant;

public enum UserStatus {
  ACTIVE(0, "有効"),
  LOCK(1, "ロック"),
  DISABLED(2, "無効");

  private int code;
  private String description;

  // コンストラクタ
  UserStatus(int code, String description) {
    this.code = code;
    this.description = description;
  }

  public int getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }

  // コードからenumを取得するメソッド
  public static UserStatus fromCode(int code) {
    for (UserStatus status : values()) {
      if (status.code == code) {
        return status;
      }
    }
    throw new IllegalArgumentException("無効なコード: " + code);
  }
}
