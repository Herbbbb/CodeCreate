package com.zhicall.factory.entity;

import java.util.List;

/**
 * 实体类
 * 
 */
public class Entity {

	// [必填]Entity名称
	private String entityName;

	// [必填]作者
	private String author;

	private int entitySteate;

	// [必填]属性，可从Office获取，或DB获取，或手动写死传入，空则创建实体类没有属性
	private List<Field> fields;

	public Entity() {
	}


	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getEntitySteate() {
		return entitySteate;
	}

	public void setEntitySteate(int entitySteate) {
		this.entitySteate = entitySteate;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Entity{");
		sb.append("entityName='").append(entityName).append('\'');
		sb.append(", author='").append(author).append('\'');
		sb.append(", entitySteate=").append(entitySteate);
		sb.append(", fields=").append(fields);
		sb.append('}');
		return sb.toString();
	}


}
