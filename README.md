# ShowCoordinatesOnDisplay
This simple library instantly displays the coordinates of the area touched by the user on the activities you specify. The library also offers several editable features.

**Developer**  **Note:** *I needed a library that did this function, and I couldn't find a similar library when I searched. (I'm sorry if there is.) I created a custom class and started using it in the project I needed. I decided to turn it into a library in case someone else needs it or if it is needed for myself again. (The first library I've published, I may have mistakes, please let me know.)*

## Example Views

### Always On Display
If the ``` coordinateDisplayView.setAlwaysDisplayCoordinates(true);``` parameter is used, the coordinates will continue to be displayed when you release the touch.

https://github.com/ozgurbyk/ShowCoordinatesOnDisplay/assets/83478420/368df34a-5e07-410f-bbc2-4e846a03912d



### Delayed Remove Coordinate View
If parameter ```coordinateDisplayView.setCloseCoordinatesWithDelayMillis(2500);``` is used, coordinate views are removed 2.5 seconds after you stop touching.

https://github.com/ozgurbyk/ShowCoordinatesOnDisplay/assets/83478420/0f81d3de-6058-4604-a3b0-0651bd07d750

### Default
If you do not want to use both parameters above, by default, when you stop touching, coordinate views will disappear after 1 second.

# Usage

Add it in your root build.gradle at the end of repositories:

```
allprojects {
	repositories {
		...
	  maven { url 'https://jitpack.io' }
  }
}
```

Add the dependency
```
	dependencies {
	        implementation 'com.github.ozgurbyk:ShowCoordinatesOnDisplay:Tag'
	}
```


Create a example function like this;

```java
public void activeShowCoordinates(Activity activity) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                ViewGroup root = (ViewGroup) activity.findViewById(android.R.id.content);

                CoordinateDisplayView coordinateDisplayView = new CoordinateDisplayView(activity);
                coordinateDisplayView.setLineColor(Color.RED);
                coordinateDisplayView.setTextColor(Color.WHITE);

                //Coordinate views are removed 2.5 seconds after you stop touching
                //coordinateDisplayView.setCloseCoordinatesWithDelayMillis(2500);

                //The coordinates will continue to be displayed when you release the touch
                //coordinateDisplayView.setAlwaysDisplayCoordinates(true);

                coordinateDisplayView.init();

                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT);
                coordinateDisplayView.setLayoutParams(params);


                root.addView(coordinateDisplayView);
            }
        });
    }
```

and call it in onCreate() or onResume() methods;
```java
activeShowCoordinates(MainActivity.this);
```
