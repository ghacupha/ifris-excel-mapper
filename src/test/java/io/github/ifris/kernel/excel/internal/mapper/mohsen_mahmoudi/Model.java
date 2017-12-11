/*
 * code https://github.com/mohsen-mahmoudi/excel-object-mapping
 */
package io.github.ifris.kernel.excel.internal.mapper.mohsen_mahmoudi;

import io.github.ifris.kernel.excel.internal.mapper.mohsen_mahmoudi.annotation.Col;
import java.util.Date;

/**
 * @author redcrow
 * @author Mohsen.Mahmoudi
 */
public class Model {

	@Col(name = "first name")
	private String fistName;

	@Col(name = "last name")
	private String lastName;

	private Integer age;

	@Col(name = "birth date", pattern = "dd/MM/yyyy")
	private Date birthdate;

	@Col(index = 4)
	private String fatherName;

	@Col(index = 5)
	private Boolean iq;

	public String getFistName() {
		return fistName;
	}

	public void setFistName(String fistName) {
		this.fistName = fistName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public Boolean getIq() {
		return iq;
	}

	public void setIq(Boolean iq) {
		this.iq = iq;
	}

}
