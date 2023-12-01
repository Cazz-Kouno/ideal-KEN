package model;

import java.io.Serializable;

public class TableLoc implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int tableId;
	private String tableName;
	private int maxCapacity;
	
	//コンストラクター
	public TableLoc() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

	public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

	public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }


}
