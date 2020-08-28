package cn.movinginfo.tztf.sev.domain;

public class Page {

	private int FirstItemOnPage;
	private boolean HasNextPage;
	private boolean HasPreviousPage;
	private boolean IsFirstPage;
	private boolean IsLastPage;
	private int LastItemOnPage;
	private int PageCount;
	private int PageNumber;
	private int PageSize;
	private long TotalItemCount;
	public int getFirstItemOnPage() {
		return FirstItemOnPage;
	}
	@Override
	public String toString() {
		return "Page [FirstItemOnPage=" + FirstItemOnPage + ", HasNextPage="
				+ HasNextPage + ", HasPreviousPage=" + HasPreviousPage
				+ ", IsFirstPage=" + IsFirstPage + ", IsLastPage=" + IsLastPage
				+ ", LastItemOnPage=" + LastItemOnPage + ", PageCount="
				+ PageCount + ", PageNumber=" + PageNumber + ", PageSize="
				+ PageSize + ", TotalItemCount=" + TotalItemCount + "]";
	}
	public void setFirstItemOnPage(int firstItemOnPage) {
		FirstItemOnPage = firstItemOnPage;
	}
	public boolean isHasNextPage() {
		return HasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		HasNextPage = hasNextPage;
	}
	public boolean isHasPreviousPage() {
		return HasPreviousPage;
	}
	public void setHasPreviousPage(boolean hasPreviousPage) {
		HasPreviousPage = hasPreviousPage;
	}
	public boolean isIsFirstPage() {
		return IsFirstPage;
	}
	public void setIsFirstPage(boolean isFirstPage) {
		IsFirstPage = isFirstPage;
	}
	public boolean isIsLastPage() {
		return IsLastPage;
	}
	public void setIsLastPage(boolean isLastPage) {
		IsLastPage = isLastPage;
	}
	public int getLastItemOnPage() {
		return LastItemOnPage;
	}
	public void setLastItemOnPage(int lastItemOnPage) {
		LastItemOnPage = lastItemOnPage;
	}
	public int getPageCount() {
		return PageCount;
	}
	public void setPageCount(int pageCount) {
		PageCount = pageCount;
	}
	public int getPageNumber() {
		return PageNumber;
	}
	public void setPageNumber(int pageNumber) {
		PageNumber = pageNumber;
	}
	public int getPageSize() {
		return PageSize;
	}
	public void setPageSize(int pageSize) {
		PageSize = pageSize;
	}
	public long getTotalItemCount() {
		return TotalItemCount;
	}
	public void setTotalItemCount(long totalItemCount) {
		TotalItemCount = totalItemCount;
	}
}
