package MyPackage;

public class Books {
	private String ISBN;			
	private String bookname;
	private String authors;		
	private String topic;
	private String publishername;
	private String image;
	private Integer booknumber;
	private double price;

	
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors=authors;
	}
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
		public String getPublishername() {
			return publishername;
		}
		public void setPublishername(String publishername) {
			this.publishername = publishername;
		}

		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}

		public Integer getBooknumber() {
			return booknumber;
		}
		public void setBooknumber(Integer booknumber) {
			this.booknumber=booknumber;
		}
		
		/*public Boolean getNeworold() {
			return neworold;
		}
		public void setNeworold(Boolean neworold) {
			this.neworold=neworold;
		}*/
		
		public Double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
			
		}
	
}
