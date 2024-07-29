# Boot-counter

Interesting task as a test :)
But the main pain was Action_BOOT_COMPLETED because you know on some devices it doesn't work without extra user actions or can be broken completely in the firmware.
It does not work with --receiver-include-background - on my emulator and real device (api34 oneui6.1 s21)
And wasted almost hour trying to trigger this action manually without success.

And from my opinion the logic of different texts for notification is a bit compicated, it's even hard to understand from the first time. maybe better to have only 2 options, 3 are a bit much).

ToDO List:
- finish and move push notification logic to use cases to get texts etc;
- add periodic workmanager task;
- improve logic to ask for notification permission;
- use any of architecture pattern (for example add simple viewmodel);
- move provides to binds where possible;
- move domain and data layers to separate modules;
- use ksp instead of kapt;
- add extra model for layers (i.e. ui model, domain data model and add mappers);
- I could make custom remoteview for custom notication layout;
- we could switch to compose, but I had experience in remote views only on regular View system, it's interesting is it possible to do the same for notifications...
- we could add some junit tests - sure if client has resources for this :) 
