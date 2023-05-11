package com.kotlin.mykotlinproj

import com.google.gson.Gson
import com.kotlin.mykotlinproj.data.model.images.UnsplashPhoto

class TestData {
    companion object {
        val dummyImage: UnsplashPhoto = Gson().fromJson<UnsplashPhoto>(
            "{\n" +
                    "            \"id\": \"dE3exzmYlKc\",\n" +
                    "            \"slug\": \"dE3exzmYlKc\",\n" +
                    "            \"created_at\": \"2020-06-28T10:54:52Z\",\n" +
                    "            \"updated_at\": \"2023-05-11T02:13:22Z\",\n" +
                    "            \"promoted_at\": null,\n" +
                    "            \"width\": 4000,\n" +
                    "            \"height\": 6000,\n" +
                    "            \"color\": \"#d9d9d9\",\n" +
                    "            \"blur_hash\": \"LpLN-;Rj.8xs_4aet7flbcWBIUof\",\n" +
                    "            \"description\": null,\n" +
                    "            \"alt_description\": \"man in black jacket holding yellow and green plastic bottle\",\n" +
                    "            \"urls\": {\n" +
                    "                \"raw\": \"https://images.unsplash.com/photo-1593341646782-e0b495cff86d?ixid=M3wyNzgxNjF8MHwxfHNlYXJjaHwxfHxjcmlja2V0fGVufDB8fHx8MTY4Mzc4NjgzNXww&ixlib=rb-4.0.3\",\n" +
                    "                \"full\": \"https://images.unsplash.com/photo-1593341646782-e0b495cff86d?crop=entropy&cs=srgb&fm=jpg&ixid=M3wyNzgxNjF8MHwxfHNlYXJjaHwxfHxjcmlja2V0fGVufDB8fHx8MTY4Mzc4NjgzNXww&ixlib=rb-4.0.3&q=85\",\n" +
                    "                \"regular\": \"https://images.unsplash.com/photo-1593341646782-e0b495cff86d?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wyNzgxNjF8MHwxfHNlYXJjaHwxfHxjcmlja2V0fGVufDB8fHx8MTY4Mzc4NjgzNXww&ixlib=rb-4.0.3&q=80&w=1080\",\n" +
                    "                \"small\": \"https://images.unsplash.com/photo-1593341646782-e0b495cff86d?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wyNzgxNjF8MHwxfHNlYXJjaHwxfHxjcmlja2V0fGVufDB8fHx8MTY4Mzc4NjgzNXww&ixlib=rb-4.0.3&q=80&w=400\",\n" +
                    "                \"thumb\": \"https://images.unsplash.com/photo-1593341646782-e0b495cff86d?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wyNzgxNjF8MHwxfHNlYXJjaHwxfHxjcmlja2V0fGVufDB8fHx8MTY4Mzc4NjgzNXww&ixlib=rb-4.0.3&q=80&w=200\",\n" +
                    "                \"small_s3\": \"https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1593341646782-e0b495cff86d\"\n" +
                    "            },\n" +
                    "            \"links\": {\n" +
                    "                \"self\": \"https://api.unsplash.com/photos/dE3exzmYlKc\",\n" +
                    "                \"html\": \"https://unsplash.com/photos/dE3exzmYlKc\",\n" +
                    "                \"download\": \"https://unsplash.com/photos/dE3exzmYlKc/download?ixid=M3wyNzgxNjF8MHwxfHNlYXJjaHwxfHxjcmlja2V0fGVufDB8fHx8MTY4Mzc4NjgzNXww\",\n" +
                    "                \"download_location\": \"https://api.unsplash.com/photos/dE3exzmYlKc/download?ixid=M3wyNzgxNjF8MHwxfHNlYXJjaHwxfHxjcmlja2V0fGVufDB8fHx8MTY4Mzc4NjgzNXww\"\n" +
                    "            },\n" +
                    "            \"likes\": 160,\n" +
                    "            \"liked_by_user\": false,\n" +
                    "            \"current_user_collections\": [],\n" +
                    "            \"sponsorship\": null,\n" +
                    "            \"topic_submissions\": {\n" +
                    "                \"athletics\": {\n" +
                    "                    \"status\": \"approved\",\n" +
                    "                    \"approved_on\": \"2020-07-27T12:59:19Z\"\n" +
                    "                }\n" +
                    "            },\n" +
                    "            \"user\": {\n" +
                    "                \"id\": \"7vY3wXFXuJY\",\n" +
                    "                \"updated_at\": \"2023-05-11T05:55:30Z\",\n" +
                    "                \"username\": \"yogendras31\",\n" +
                    "                \"name\": \"Yogendra Singh\",\n" +
                    "                \"first_name\": \"Yogendra\",\n" +
                    "                \"last_name\": \"Singh\",\n" +
                    "                \"twitter_username\": \"ysphotomedia\",\n" +
                    "                \"portfolio_url\": null,\n" +
                    "                \"bio\": null,\n" +
                    "                \"location\": \"New delhi\",\n" +
                    "                \"links\": {\n" +
                    "                    \"self\": \"https://api.unsplash.com/users/yogendras31\",\n" +
                    "                    \"html\": \"https://unsplash.com/@yogendras31\",\n" +
                    "                    \"photos\": \"https://api.unsplash.com/users/yogendras31/photos\",\n" +
                    "                    \"likes\": \"https://api.unsplash.com/users/yogendras31/likes\",\n" +
                    "                    \"portfolio\": \"https://api.unsplash.com/users/yogendras31/portfolio\",\n" +
                    "                    \"following\": \"https://api.unsplash.com/users/yogendras31/following\",\n" +
                    "                    \"followers\": \"https://api.unsplash.com/users/yogendras31/followers\"\n" +
                    "                },\n" +
                    "                \"profile_image\": {\n" +
                    "                    \"small\": \"https://images.unsplash.com/profile-fb-1568987997-0c82e523102d.jpg?ixlib=rb-4.0.3&crop=faces&fit=crop&w=32&h=32\",\n" +
                    "                    \"medium\": \"https://images.unsplash.com/profile-fb-1568987997-0c82e523102d.jpg?ixlib=rb-4.0.3&crop=faces&fit=crop&w=64&h=64\",\n" +
                    "                    \"large\": \"https://images.unsplash.com/profile-fb-1568987997-0c82e523102d.jpg?ixlib=rb-4.0.3&crop=faces&fit=crop&w=128&h=128\"\n" +
                    "                },\n" +
                    "                \"instagram_username\": \"yogendrasingh.in\",\n" +
                    "                \"total_collections\": 0,\n" +
                    "                \"total_likes\": 0,\n" +
                    "                \"total_photos\": 510,\n" +
                    "                \"accepted_tos\": true,\n" +
                    "                \"for_hire\": true,\n" +
                    "                \"social\": {\n" +
                    "                    \"instagram_username\": \"yogendrasingh.in\",\n" +
                    "                    \"portfolio_url\": null,\n" +
                    "                    \"twitter_username\": \"ysphotomedia\",\n" +
                    "                    \"paypal_email\": null\n" +
                    "                }\n" +
                    "            },\n" +
                    "            \"tags\": [\n" +
                    "                {\n" +
                    "                    \"type\": \"search\",\n" +
                    "                    \"title\": \"team sport\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"type\": \"search\",\n" +
                    "                    \"title\": \"hit a ball\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"type\": \"search\",\n" +
                    "                    \"title\": \"ball sport\"\n" +
                    "                }\n" +
                    "            ]\n" +
                    "        }", UnsplashPhoto::class.java
        )
    }
}