package de.uniba.wiai.dsg.ajp.assignment3;

/**
 * Represents a film.
 * 
 * A film has a title, a picture quality and a price. There are different film
 * categories and thus different prices for regular, children's or new released
 * films.
 * 
 */
public class Movie {

	private Price price;

	private PictureQuality pictureQuality;

	private String title;

	/**
	 * Constructor that initializes the {@code Movie} with a given title, PriceCode and PictureQuality.
	 * <br>
	 * Postcondition: 
	 * <ul>
	 * <li>{@code getTitle() == title}</li>
	 * <li>{@code getPriceCode() == priceCode}</li>
	 * <li>{@code getPictureQuality() == pictureQuality}</li>
	 * </ul>
	 * 
	 * @param title
	 * 		Title of the {@code Movie}
	 * @param priceCode
	 * 		PriceCode of the {@code Movie}
	 * @param pictureQuality
	 * 		PictureQuality of the {@code Movie}
	 */
	public Movie(String title, PriceCode priceCode, PictureQuality pictureQuality) {
		if (title != null && title != "") {
			this.title = title;
		} else {
			throw new IllegalArgumentException("Error. 'title' must not be null or empty.");
		}

		this.setPriceCode(priceCode);

		if (pictureQuality != null) {
			this.pictureQuality = pictureQuality;
		} else {
			throw new IllegalArgumentException("Error. 'pictureQuality' must not be null");
		}

	}

	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of a movie.
	 * 
	 * <p>
	 * Precondition:
	 * <ul>
	 * <li>The title must not be <code>null</code> or empty.</li>
	 * 
	 * @param title the new title to be set.
	 */
	public void setTitle(String title) {
		if (title != null && title != "") {
			this.title = title;
		} else {
			throw new IllegalArgumentException("Error. 'title' must not be null or empty.");
		}
	}

	/**
	 * Gets the actual charges from the rental of one film for a certain number of
	 * days.
	 * 
	 * <p>
	 * Precondition:
	 * <ul>
	 * <li>The movie must be available in the video store.</li>
	 * <li>The movie must have been rented.</li>
	 * </ul>
	 * 
	 * <p>
	 * Postcondition:
	 * <ul>
	 * <li>The charge will be calculated.</li>
	 * </ul>
	 * 
	 * @param daysRented The amount of days the movie was rented. must not be 0 or
	 *                   negative.
	 * 
	 * @return the amount of charge calculated.
	 * 
	 * @throws IllegalArgumentException if daysRented is invalid.
	 */
	double getCharge(int daysRented) {
		double charge = 0;

		if (daysRented > 0) {

			charge = price.getCharge(daysRented);

			if (pictureQuality == PictureQuality.RES_4K) {
				charge += 2;
			}

			return charge;

		} else {
			throw new IllegalArgumentException("Error. 'daysRented' must be greater than 0.");
		}

	}

	/**
	 * Gets the category of a film.
	 * 
	 * @return the price code of the film category.
	 */
	public PriceCode getPriceCode() {
		return price.getPriceCode();
	}

	/**
	 * Sets a price factor to a film category.
	 * 
	 * @param priceCode the price code associated with a specific film category.
	 *                  Must not be null.
	 * 
	 * @throws IllegalArgumentException if priceCode is invalid.
	 */
	public void setPriceCode(PriceCode priceCode) {
		switch (priceCode) {
		case REGULAR:
			price = new RegularPrice();
			break;
		case CHILDRENS:
			price = new ChildrensPrice();
			break;
		case NEW_RELEASE:
			price = new NewReleasePrice();
			break;
		default:
			throw new IllegalArgumentException("Incorrect Price Code");
		}
	}

	public PictureQuality getPictureQuality() {
		return pictureQuality;
	}

	/**
	 * Sets a picture quality to a movie.
	 * 
	 * @param pictureQuality The picture quality to be set. Must not be null.
	 * 
	 * @throws IllegalArgumentException if the picture quality is invalid.
	 */
	public void setPictureQuality(PictureQuality pictureQuality) {
		if (pictureQuality != null) {
			this.pictureQuality = pictureQuality;
		} else {
			throw new IllegalArgumentException("Error. 'pictureQuality' must not be null");
		}
	}

	/**
	 * Gets the frequent renter points collected by the rental of one film for a
	 * certain number of days.
	 * 
	 * @param daysRented The amount of days the movie was rented. Must not be 0 or
	 *                   negative.
	 * 
	 * @return A number of frequent renter points.
	 * 
	 * @throws IllegalArgumentException if daysRented is invalid.
	 */
	public int getFrequentRenterPoints(int daysRented) {
		if (daysRented > 0) {
			return price.getFrequentRenterPoints(daysRented);
		} else {
			throw new IllegalArgumentException("Error. 'daysRented' must be greater than 0.");
		}
	}
}
