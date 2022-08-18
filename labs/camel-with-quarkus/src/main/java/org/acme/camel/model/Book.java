package org.acme.camel.model;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;

import io.quarkus.runtime.annotations.RegisterForReflection;

@CsvRecord(separator = ",")
@RegisterForReflection
public class Book {

  private int id;
  private String author;
  private String title;
  private String genre;


  /**
   * @param id
   * @param author
   * @param title
   * @param genre
   */
  public Book(int id, String author, String title, String genre) {
    this.id = id;
    this.author = author;
    this.title = title;
    this.genre = genre;
  }


  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }


  /**
   * @param author the author to set
   */
  public void setAuthor(String author) {
    this.author = author;
  }


  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }


  /**
   * @param genre the genre to set
   */
  public void setGenre(String genre) {
    this.genre = genre;
  }


  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  
  @Override
  public String toString() {
    return "Book [author=" + author + ", genre=" + genre + ", id=" + id + ", title=" + title + "]";
  }  
  
}
