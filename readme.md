<h1 align="center">
  <span><h3>Clearcals Assignment</h3></span>
    <p align="center">
    <a href="https://developer.android.com/studio"><img src="https://img.shields.io/badge/Built%20With-Android%20Studio-green?style=for-the-badge" alt="Android Studio"/></a>
    <a href="https://developer.android.com/studio"><img src="https://img.shields.io/badge/Launguages-Kotlin-blue?style=for-the-badge" alt="Kotlin"/></a>
    <a href="https://developer.android.com/studio"><img src="https://img.shields.io/badge/Architecture%20Used-MVVM-critical?style=for-the-badge" alt="Kotlin"/></a>
  </p>
</h1>


## Overview

This is an food recipe application that provides textual and video-based recipes. In addition, it allows users to search for recipes by name, ingredients, or tags (which are labels that are attached to a recipe to categorize it). 


## Features

- User can get the recipe based on the category.
- User can search for the particular recipe.
- Pagination is implemented for better user experience.

## Architecture

#### MVVM (Model View View Model) Architecture

<img src="/docs/images/mvvm.png" width="32%" alt="mvvm architecture"/>

## Tech Stacks

- Android Studio
- Kotlin
- Libraries used
    - Retrofit
    - TastyAPI
    - Picasso

## How to build it

Clone this repository in your system, then click on Build in Toolbar and select Build APK

This might give error because you have not specified the BASE_URL and API_KEY.
<br>So to remove this errors we need to create a package named utils and create a Constants.kt inside it.

Your Constants.kt should look like this:

```
package com.hitesh.clearcalsassignment.utils

object Constants {
    const val BASE_URL = "https://tasty.p.rapidapi.com/"
    const val API_KEY = "YOUR API KEY"
}
```
    
## Screenshots

<p align="center">
  <img src="/docs/images/img1.jpg" width="26%" alt="Image 1"/>
  <img src="/docs/images/img2.jpg" width="26%" alt="Image 2"/>
  <img src="/docs/images/img3.jpg" width="26%" alt="Image 3"/>
  <img src="/docs/images/img4.jpg" width="26%" alt="Image 4"/>
  <img src="/docs/images/img5.jpg" width="26%" alt="Image 5"/>
  <img src="/docs/images/img6.jpg" width="26%" alt="Image 6"/>
</p>

Video Link: https://drive.google.com/file/d/1Mlwm2GFs4iQo-o6N_992Cu6OV4ktDcrM/view?usp=share_link

## Support

- If you have any issues, feel free to reach out [hiteshsukhwani29@gmail.com](mailto:hiteshsukhwani29@gmail.com).
