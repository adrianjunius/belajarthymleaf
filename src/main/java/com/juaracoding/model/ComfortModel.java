package com.juaracoding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comfort")
public class ComfortModel {
	@Id
	@Column(length =20)
	String title;
	String deskripsi;
	String gambar;
}
