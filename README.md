# Hanaten (放出)

A/B testing for Android.

## Usage

Check out the included example project to see everything in action.

### Split test

```java
String color = new Splitter<String>(context, "test name")
  .add(10, "Red")
  .add(20, "Green")
  .add(30, "Blue")
  .split();
```

### A/B test

```
new Hanaten(context, "test name")
  .add(10, new Runnable() {
    @Override
    void run() {
      mPresenter.showRed();
    }
  })
  .add(20, new Runnable() {
    @Override
    void run() {
      mPresenter.showGreen();
    }
  })
  .add(30, new Runnable() {
    @Override
    void run() {
      mPresenter.showBlue();
    }
  })
  .start();
```

## LICENSE

MIT
