package teste;

public class CreateDB {
	/*String MESSAGE_TAGS = "CREATE TABLE IF NOT EXISTS message_tags ("+
	        				"ID TEXT PRIMARY KEY,"+
	        				"TAG_ID TEXT NOT NULL"+	        				
	        				")";
	Hibernate: 
	    CREATE TABLE IF NOT EXISTS TB_ADDRESS_BOOK (
	        id TEXT NOT NULL,
	        address TEXT,
	        nick TEXT,
	        primary key (id)
	    )
	Hibernate: 
	    CREATE TABLE IF NOT EXISTS TB_FOLDER (
	        id TEXT NOT NULL,
	        NAME varchar(512),
	        primary key (id)
	    )
	Hibernate: 
	    CREATE TABLE IF NOT EXISTS TB_FOLDER_TB_FOLDER_CONTENT (
	        IrisFolder_id TEXT NOT NULL,
	        contents_id TEXT NOT NULL
	    )
	Hibernate: 
	    CREATE TABLE IF NOT EXISTS TB_MESSAGE (
	        id TEXT NOT NULL,
	        BCC_RECIPIENT varchar(1024),
	        CC_RECIPIENT varchar(1024),
	        DATE datetime,
	        MSG_FROM varchar(1024),
	        MESSAGE_CONTENT TEXT,
	        SUBJECT varchar(1024),
	        RECIPIENT varchar(1024),
	        FOLDER_ID TEXT NOT NULL,
	        primary key (id)
	    )
	Hibernate: 
	    CREATE TABLE IF NOT EXISTS TB_TAG (
	        TAG_ID TEXT NOT NULL,
	        name TEXT,
	        primary key (TAG_ID)
	    )
	    
	    private static final String CREATE_TABLE_FOLDER = 
		"CREATE TABLE IF NOT EXISTS folder("
		+"  id    TEXT PRIMARY KEY," 
		+"  name  TEXT"
		+");";

private static final String CREATE_TABLE_MESSAGE =
		"CREATE TABLE IF NOT EXISTS message("
		  +"id       TEXT PRIMARY KEY," 
		  +"_from    TEXT,"
		  +"_to       TEXT,"
		  +"cc       TEXT,"
		  +"bcc      TEXT,"
		  +"subject  TEXT,"
		  +"message  TEXT,"
		  +"_date     TIMESTAMP," 
		  +"folderid INTEGER,"
		  +"FOREIGN KEY(folderid) REFERENCES folder(id)"
		+");";	
*/
}
