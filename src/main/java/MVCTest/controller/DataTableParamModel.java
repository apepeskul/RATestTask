package MVCTest.controller;

public class DataTableParamModel {

  /**
   * the request sequence number sent by DataTable
   */
  public String sEcho;

  /**
   * the searchText
   */
  public String sSearch;

  /**
   * the number of entries shown in the table
   */
  public int iDisplayLength;

  /**
   * the first entry to be shown (used for paging)
   */
  public int iDisplayStart;

  /**
   * the number of columns
   */
  public int iColumns;

  /**
   * the number of columns used in sorting
   */
  public int iSortingCols;

  /**
   * the index of the column currently sorted
   */
  public Integer iSortColumnIndex;

  /**
   * the sorting direction ("asc" or desc")
   */
  public String sSortDirection;

  /**
   * list of column names (seperated with a comma)
   */
  public String sColumns;
}
