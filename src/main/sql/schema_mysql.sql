Create Table mahasiswa (
	ID INT PRIMARY KEY AUTO_INCREMENT,
    npm VARCHAR(255) not null,
    nama varchar(255) not null,
    tempat_lahir varchar(255) not null,
    tanggal_lahir date not null,
    jenis_kelamin varchar(255) not null,
    alamat varchar(255) not null
) ENGINE=InnoDB;
