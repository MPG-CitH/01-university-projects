package de.uniba.wiai.dsg.ajp.assignment3;

public class Rental {

	private int daysRented;
	private Movie movie;

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		if (movie != null) {
			this.movie = movie;
		} else {
			throw new IllegalArgumentException("Error. 'movie' must not be null.");
		}
	}

	public int getDaysRented() {
		return daysRented;
	}

	public void setDaysRented(int daysRented) {
		if (daysRented > 0) {
			this.daysRented = daysRented;
		} else {
			throw new IllegalArgumentException("Error. 'daysRented' must be greater than 0.");
		}
	}

	public double getCharge() {
		return movie.getCharge(daysRented);
	}

	public int getFrequentRenterPoints() {
		return movie.getFrequentRenterPoints(daysRented);
	}

}
