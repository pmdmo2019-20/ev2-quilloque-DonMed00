package es.iessaladillo.pedrojoya.quilloque.data

/* Consulta para llamadas recientes
   "SELECT C.id AS callId, C.phoneNumber AS phoneNumber, C.type AS type, \n" +
   "C.date AS date, C.time AS time, T.id AS contactId, T.name AS contactName \n" +
   "FROM Call AS C LEFT JOIN Contact AS T ON C.phoneNumber = T.phoneNumber \n" +
   "ORDER BY C.id DESC LIMIT :limit"
*/

/* Consulta para sugerencias
   "SELECT name AS contactName, phoneNumber AS phoneNumber " +
   "FROM Contact " +
   "WHERE phoneNumber like :phoneNumber " +
   "UNION " +
   "SELECT DISTINCT phoneNumber AS contactName, phoneNumber AS phoneNumber " +
   "FROM Call " +
   "WHERE phoneNumber like :phoneNumber " +
   "AND phoneNumber NOT IN (SELECT phoneNumber FROM Contact)")
*/
