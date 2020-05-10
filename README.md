# ReversibleSlider
[ ![Download](https://api.bintray.com/packages/ma7madfawzy/reversibleslider/com.widget.reversibleslider/images/download.svg) ](https://bintray.com/ma7madfawzy/reversibleslider/com.widget.reversibleslider/_latestVersion)

An Android library that lets you create an animated slider widget from your data list in a simple and fast way.

![sample](images/Demo2.gif)

## Quick Setup

### 1- Include library

#### Using Gradle
```
dependencies {
    implementation 'com.widget.reversibleslider:1.0.0'
}
```
#### Using Maven
```
<dependency>
  <groupId>com.widget</groupId>
  <artifactId>reversibleslider</artifactId>
  <version>1+</version>
  <type>pom</type>
</dependency>

```
## 2- Usage

### First put the slider view in your layout xml :

 ```
      <com.widget.reversible_slider.CustomSlider
                    android:id="@+id/reversible_slider"
                    android:layout_width="match_parent"
                    android:layout_height="200dp"
                    android:layout_marginTop="16dp" />

```
### Next Step:

#### Pass you ites list as Strings list ( for example: list of images urls)
````
    CustomSlider slider = findViewById(R.id.reversible_slider);
    slider.setData(generateDummy());
    
    @NotNull
    private List<String> generateDummy() {
        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("https://i0.wp.com/cdn-prod.medicalnewstoday.com/content/images/articles/325/325466/man-walking-dog.jpg?w=1155&h=1541");
        imageUrls.add("https://scx2.b-cdn.net/gfx/news/hires/2019/2-nature.jpg");
        imageUrls.add("https://i0.wp.com/cdn-prod.medicalnewstoday.com/content/images/articles/325/325466/man-walking-dog.jpg?w=1155&h=1541");
        imageUrls.add("https://i0.wp.com/cdn-prod.medicalnewstoday.com/content/images/articles/325/325466/man-walking-dog.jpg?w=1155&h=1541");
        imageUrls.add("https://www.scitecheuropa.eu/wp-content/uploads/2019/09/Nature-climate.jpg");
        return imageUrls;
    }
````
  You can set your desired animation time by calling:
  ````
  slider.setAnimationTime(8000); // 8000-> animation interval time in milliseconds
  ````
### Happy Coding

## Authors

* [Mohammed Fawzy](https://github.com/ma7madfawzy)
* [Ali Gamal](https://github.com/aligamal-dev)
* [Muhammad Noamany](https://github.com/muhammadnomany25)


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

