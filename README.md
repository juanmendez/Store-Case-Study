## Target DealBrowser

This is a small project which is using the MVP with a variation of VM pattern. Several features benefit
greatly in binding Model with Presenter, and View, leaving behind a close tightness between View and Presenter.
Because this is a small project, it uses `Android Annotations` which is a good alternative between Dagger and
Butterknife. In order to cache data upon rotation this small project uses `Android Architecture components` such
as `LiveData` and `ViewModel`

This small project supports tablets having a landscape view with two panes. The toolbar in this project
belongs to `MainActivity`, and it ensures to propagate menu option clicks to `Fragments` as well.

This is a single `Activity` application, and in order to work with different `Fragments` it's using
another self project called `ShoeBoxes` which is used to display `Fragments` based on navigation,
and keeps the path to each one visited in order to go back through backstack, or back button at the toolbar.

Tools:
Kotlin language
Android Annotations
ViewModel
ShoeBoxes
DataBinding

