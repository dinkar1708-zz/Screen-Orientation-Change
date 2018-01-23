# Screen-Orientation-Change
Handling Configuration Changes, Handling of such a unwanted behavior Handling poor user experience A.Retaining an Object During a Configuration Change - Let activity be recreated, handled data manually. B.Handle the configuration change yourself - Prevent Activity to recreated - This is not recommended by Android.
# added module without config changes.


# Handling Configuration Changes -
# Problem-
When device configurations changes during runtime (such as screen orientation, keyboard availability, and language) then Android restarts the running Activity (onDestroy() is called, followed by onCreate()).
As a result user loses saved data on screen, some other unwanted behavior, this makes bad user experience.
My solution handle below case – I cannot fix the orientation as done below, user must be able to use application in both the mode landscape and portrait.
<activity
   android:screenOrientation="portrait"
   ...  />
Before proceeding to tutorial make sure than device screen rotation is on from settings.
Lets handle such scenario and see the impact in both the cases-
# Handling of such a unwanted behavior / Handling poor user experience-
A.	Retaining an Object During a Configuration Change - Let activity be recreated, handled data manually.
B.	Handle the configuration change yourself - Prevent Activity to recreated - This is not recommended by Android.

