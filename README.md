I have used ChatGPT to resolve below problems:

#### Best approach when creating Kotlin models
- I planned to use getters and setters but I think Kotlin has a proper approach.
- I used Kotlin's data class since it's clean and immutable. It also has auto-generated functions such as copy(), equals(), and toString() by default.

#### Best way to send an event from fragment to activity
- My initial idea is to use interface callbacks but I tried to look for cleaner and more modern approach.
- I used shared view models since it's lifecycle aware, survives configuration changes, and has no tight coupling.

#### Best approach to load an online image
- I had to display the user's avatar in ImageView.
- I used Coil library. It's lightweught and easy to use with minimal setup.

#### How to fix GitHub's request rate limit
- I encountered an issue where my github api requests stopped working. I found that github's unauthenticated request has rate limit by default.
- Solution: Create personal access token in my GitHub account, and add it to the authorization header of every requests.
- This app will use the public GitHub REST API by default. To increase rate limits (optional):
  1. Create a GitHub Personal Access Token
  2. Add it to `gradle.properties`: github.token=your_token_here
