package com.back.base.model;


/**
 * 池中编号Vo
 */
public class CodePoolVo {
	/**
	 * ID
	 */
	private String id;
	
	/**
	 * 关键字
	 */
	private String keyValue;
	
	/**
	 * 编号
	 */
	private String codeValue;
	
	/**
	 * 编号有效期
	 */
	private String validity;
	
	/**
	 * 编号占用后释放时间
	 */
	private String releaseValue;
	
	/**
	 * 重新编号时间
	 */
	private String recode;
	
	/**
	 * 所属编号设置
	 */
	private CodeSetupVo codeSetupVo;
	
	/**
	 * @return the validity
	 */
	public String getValidity() {
		return validity;
	}
	/**
	 * @param validity the validity to set
	 */
	public void setValidity(String validity) {
		this.validity = validity;
	}
	/**
	 * @return the codeSetupVo
	 */
	public CodeSetupVo getCodeSetupVo() {
		return codeSetupVo;
	}
	/**
	 * @param codeSetupVo the codeSetupVo to set
	 */
	public void setCodeSetupVo(CodeSetupVo codeSetupVo) {
		this.codeSetupVo = codeSetupVo;
	}
	/**
	 * @return the recode
	 */
	public String getRecode() {
		return recode;
	}
	/**
	 * @param recode the recode to set
	 */
	public void setRecode(String recode) {
		this.recode = recode;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	public String getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	public String getCodeValue() {
		return codeValue;
	}
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}
	public String getReleaseValue() {
		return releaseValue;
	}
	public void setReleaseValue(String releaseValue) {
		this.releaseValue = releaseValue;
	}
	
	private String key;
	private String table;
	private String column;
	private String prefixKeySetup;
	private String condititon;

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the table
	 */
	public String getTable() {
		return table;
	}
	/**
	 * @param table the table to set
	 */
	public void setTable(String table) {
		this.table = table;
	}
	/**
	 * @return the column
	 */
	public String getColumn() {
		return column;
	}
	/**
	 * @param column the column to set
	 */
	public void setColumn(String column) {
		this.column = column;
	}
	/**
	 * @return the prefixKeySetup
	 */
	public String getPrefixKeySetup() {
		return prefixKeySetup;
	}
	/**
	 * @param prefixKeySetup the prefixKeySetup to set
	 */
	public void setPrefixKeySetup(String prefixKeySetup) {
		this.prefixKeySetup = prefixKeySetup;
	}
	/**
	 * @return the condititon
	 */
	public String getCondititon() {
		return condititon;
	}
	/**
	 * @param condititon the condititon to set
	 */
	public void setCondititon(String condititon) {
		this.condititon = condititon;
	}
	
}
