# clown-hub
Simple project utilising JokeAPI https://sv443.net/jokeapi/v2/
Pretty standard setup, no Usecase/Domain layer since project is way too small for that.
Higlights: (new stuff for me)
- Realm for local data storage https://realm.io/
- Turbine for coroutines testing
- Material3 dynamic themes (did not enjoy absence of basic elements in Material3, cmon its been two years already!)
Works for Android 12+ only due to dynamic theming (which usage I wanted to enforce). Change wallpapers to see colors change!


TODO:
- Move dependencies versions away into dedicated Versions file
- Inject CoroutineDispatcher into repository instead of hardcoding it on VM level
- Swipe to refresh functionality (Material3 still does not have it!)
- Setting to clear local cache (RealmDB) and delete items
- Domain layer if app grows bigger
