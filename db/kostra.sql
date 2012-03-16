CREATE TABLE Roles (
  idRoles INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(32) NULL,
  description VARCHAR(128) NULL,
  PRIMARY KEY(idRoles)
)
;

CREATE TABLE Report (
  idReport INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR NOT NULL,
  date DATETIME NOT NULL,
  latitude FLOAT(9) NOT NULL,
  longitude FLOAT(9) NOT NULL,
  describtion VARCHAR(256) NULL,
  PRIMARY KEY(idReport)
)
;

CREATE TABLE Users (
  idUsers INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  username VARCHAR(64) NOT NULL DEFAULT guest,
  passwd VARCHAR(64) NOT NULL,
  role INTEGER UNSIGNED NOT NULL,
  phoneId VARCHAR(64) NULL,
  PRIMARY KEY(idUsers),
  INDEX Users_FKIndex1(role),
  FOREIGN KEY(role)
    REFERENCES Roles(idRoles)
      ON DELETE RESTRICT
      ON UPDATE CASCADE
)
;

CREATE TABLE Tip (
  idTip INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Report_idReport INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(idTip, Report_idReport),
  INDEX Tip_FKIndex1(Report_idReport),
  FOREIGN KEY(Report_idReport)
    REFERENCES Report(idReport)
      ON DELETE CASCADE
      ON UPDATE CASCADE
)
;

CREATE TABLE Photos (
  idPhotos INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Report_idReport INTEGER UNSIGNED NOT NULL,
  url VARCHAR(256) NULL,
  PRIMARY KEY(idPhotos),
  INDEX Photos_FKIndex1(Report_idReport),
  FOREIGN KEY(Report_idReport)
    REFERENCES Report(idReport)
      ON DELETE CASCADE
      ON UPDATE CASCADE
)
;

CREATE TABLE Place (
  idPlace INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Report_idReport INTEGER UNSIGNED NOT NULL,
  accesibility INTEGER UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY(idPlace, Report_idReport),
  INDEX Place_FKIndex1(Report_idReport),
  FOREIGN KEY(Report_idReport)
    REFERENCES Report(idReport)
      ON DELETE CASCADE
      ON UPDATE CASCADE
)
;

CREATE TABLE Problem (
  idProblem INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Report_idReport INTEGER UNSIGNED NOT NULL,
  expiration DATETIME NOT NULL,
  PRIMARY KEY(idProblem, Report_idReport),
  INDEX Problem_FKIndex1(Report_idReport),
  FOREIGN KEY(Report_idReport)
    REFERENCES Report(idReport)
      ON DELETE CASCADE
      ON UPDATE CASCADE
)
;

CREATE TABLE Logs (
  idLogs INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Report_idReport INTEGER UNSIGNED NOT NULL,
  Users_idUsers INTEGER UNSIGNED NOT NULL,
  operation INTEGER UNSIGNED NOT NULL,
  date DATETIME NOT NULL,
  PRIMARY KEY(idLogs),
  INDEX Logs_FKIndex1(Users_idUsers),
  INDEX Logs_FKIndex2(Report_idReport),
  FOREIGN KEY(Users_idUsers)
    REFERENCES Users(idUsers)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
  FOREIGN KEY(Report_idReport)
    REFERENCES Report(idReport)
      ON DELETE CASCADE
      ON UPDATE CASCADE
);

