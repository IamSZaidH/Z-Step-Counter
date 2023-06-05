# Z Step Counter

Z Step Counter is an Android application that utilizes the Google Fit API to access step count data and presents it in a user-friendly manner. This README file provides an overview of the application, instructions on how to run it, and details about the design choices and difficulties encountered during development.

<img src="https://github.com/IamSZaidH/Z-Step-Counter/blob/master/app/src/main/res/drawable/ic_launcher.png" style="margin-right: 10px;" width='150px' height='150px'>
<a href="https://drive.google.com/u/0/uc?id=120jPqrZ6H_rCmjZhhhm03nqaVPT_DbLX&export=download" rel="nofollow"><img src="https://camo.githubusercontent.com/f8cc865a8fa303cbf10e8d0451254fa21c07163dc23a5becc9c174f28f4028f7/68747470733a2f2f706c61792e676f6f676c652e636f6d2f696e746c2f656e5f75732f6261646765732f7374617469632f696d616765732f6261646765732f656e5f62616467655f7765625f67656e657269632e706e67" alt="Get it on Google Play" height="80" data-canonical-src="https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png" style="max-width: 100%;"></a>

## Screenshot 

<div style="display: flex; justify-content: center;">
  <img src="https://github.com/IamSZaidH/Z-Step-Counter/assets/91463783/bd0be98c-cd03-480e-8710-7ff8b9dfd2c9" style="margin-right: 10px;">
  <img src="https://github.com/IamSZaidH/Z-Step-Counter/assets/91463783/a0a3cef5-1af7-41c3-8b65-3d181f4c97f1" style="margin-right: 10px;">
  <img src="https://github.com/IamSZaidH/Z-Step-Counter/assets/91463783/69f746cb-5bc2-449f-b476-a0b98f126a38" style="margin-right: 10px;">
  <img src="https://github.com/IamSZaidH/Z-Step-Counter/assets/91463783/65095aeb-2d64-4f3f-9825-112ecca5d9b9" style="margin-right: 10px;">
  <img src="https://github.com/IamSZaidH/Z-Step-Counter/assets/91463783/976db8ae-229e-413c-b229-15ad4c5bdc5c">
</div>

## Features

- Authorization: The app asks the user for permission to access their Google Fit data specifically for step count.
- Step Count Retrieval: The app retrieves step count data from the Google Fit API for the past 24 hours.
- Data Presentation: The step count data is displayed in a TextView, allowing the user to easily view their step count.
- Data Refresh: The app automatically refreshes the data each time it becomes active, ensuring up-to-date step count information.

## Installation

To run the Z Step Counter application, follow these steps:

1. Clone the repository to your local machine using the following command:

   ```
   git clone https://github.com/IamSZaidH/Z-Step-Counter.git
   ```

2. Open Android Studio and select "Open an existing Android Studio project."
3. Navigate to the cloned project directory and select it to open the project in Android Studio.
4. Build the project by selecting "Build" from the toolbar and then "Make Project."

## Usage

1. Connect your Android device to your computer and make sure it is set up for development (USB debugging enabled).
2. In Android Studio, click the "Run" button or use the keyboard shortcut (Shift + F10) to run the app on your connected device or emulator.
3. The app will be installed and launched on your device.
4. Grant the necessary permissions and authorize the app to access your Google Fit data.
5. Once authorized, the step count data will be retrieved and displayed in the app's UI.
6. Each time you open the app or manually refresh the data by clicking the "Refresh" button, the step count information will be updated.

## Design Choices

- **User Interface**: The app's user interface consists of a single TextView to display the step count data. This choice was made to keep the interface clean and straightforward, focusing primarily on presenting the step count information clearly.
- **Automatic Data Refresh**: The decision to automatically refresh the data each time the app becomes active ensures that the user always sees the most recent step count. Additionally, a "Refresh" button could be added to allow manual updating of the data.
- **Past 24 Hours**: For simplicity, the app retrieves step count data for the past 24 hours. This timeframe provides a concise overview of the user's recent activity without overwhelming them with excessive data.

## Difficulties Encountered

During the development of the Z Step Counter app, the following difficulties were encountered:

1. **Google Fit API Integration**: Integrating the Google Fit API and understanding its various components required thorough research and reading the official documentation. Overcoming the initial learning curve was a challenge, but it provided valuable insights into leveraging the API effectively.
2. **Permission Handling**: Ensuring proper handling of permissions and requesting the necessary authorization from the user was critical. Understanding the permission model and implementing the request flow required careful attention to detail.
3. **Error Handling**: Implementing appropriate error handling for cases where data access is denied or other errors occur was essential to provide a smooth user experience. Ensuring graceful error messages and informative prompts helped enhance the app's usability.

## Conclusion

Z Step Counter is an Android application that uses the Google Fit API to access step count data, process it, and present it in a user-friendly manner. By following the instructions provided in the installation section, you can run the app on your Android device or emulator. The app's straightforward UI design, automatic data refreshing, and error handling make

 it an effective tool for monitoring step count activity. Feel free to explore and enhance the app based on your requirements or preferences.


