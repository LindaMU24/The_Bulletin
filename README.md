# The_Bulletin

Welcome to The Bulletin.

This is a school project made with IntelliJ Java, MySQL, Spring boot, REST API, CRUD and JPA. To run this project you need to:

Clone this repository.
Copy and paste the pre-written database setup in MySQL (you'll find it below).
Adjust your login and password for MySQL in the resource application.properties.
Use Postman to test the API.

Check it out for yourself! Hope you like it.

SQL-Script for MySQL:

CREATE DATABASE bulletin_db; 
USE bulletin_db;

Postman:
Base-URL : http://127.0.0.1:8080

**Create**

User:

Create new user with POST using the Base-URL followed by /users.
Body(JSON) example:

{
"username": "John Doe",
"email": "john.doe@example.com"
}

Channel:

Create new channel with POST using the Base-URL followed by /channels.

Body(JSON) example:

{
"title": "Computers & It"
}

Posts:

To make a post in your channel use PUT and Base-URL followed by /channels/{id}/messages
The {id} should be replaced by the given id of the channel, e.g. 1.

Body(JSON) example:

{
"title": "The best computer for programming?",
"content": "I'm considering buying a new computer for programming. Would a Laptop or a stationary be best?",
"user": {
"id": 1
}
}

Feel free to create as many users, channels and posts you like.

**Read:**

Users:

To see all users: GET using the Base-URL followed by /users/all.
This will list the users and the posts they made.

To see user with id: GET using the Base-URL followed by /users/{id}.
The {id} should be replaced with the user id you want to see, e.g. 1.
This will list the user and the posts they made.

To search a user: GET using the Base-URL followed by /users/search.
In Postman click on Params - write username in key- write the value you are searching, e.g john.
This will list the user and the posts they made.

Channels:

To see all channels: GET using the Base-URL followed by /channels.
This will list all the channels.

To see all posts in a channel: GET using the Base-URL followed by /channels/{id}/messages
The {id} should be replaced by the given id of the channel, e.g. 1.
This will list all posts in that channel.

Posts:

To see a specific post: GET using the Base-URL followed by /posts/{id}.
The {id} should be replaced by the given id of the post, e.g. 1.
This will show that post.

To see a users posts: GET using the Base-URL followed by /posts/user/{id}.
The {id} should be replaced by the given id of the user, e.g. 1.
This will show that users posts.

**Update**

Users:

To update a user with PUT using the Base-URL followed by /users/{id}.
The {id} should be replaced by the given id of the user, e.g. 1.

Body(JSON) example:

{
"username": "John Doe",
"email": "johnny@example.com"
}

Channels:

To update a channel with PUT using the Base-URL followed by /channels/{id}.
The {id} should be replaced by the given id of the channel, e.g. 1.

Body(JSON) example:

{
"title": "Computers"
}

Posts:

To update a post with PUT using the Base-URL followed by /posts/{id}.
The {id} should be replaced by the given id of the channel, e.g. 1.

Body (JSON) example:

{
"title": "The best computer for programming?",
"content": "Would a Laptop or a stationary be best?"
}

**Delete**

Users:

To delete a user with DELETE using the Base-URL followed by /users/{id}.
The {id} should be replaced by the given id of the user, e.g. 1.
This will delete the user and the posts that user made.

Channels:

To delete a channel with DELETE using the Base-URL followed by /channels/{id}.
The {id} should be replaced by the given id of the channel, e.g. 1.
This will delete the channel and the posts in the channel.

Posts:

To delete a post with DELETE using the Base-URL followed by /posts/{id}.
The {id} should be replaced by the given id of the post, e.g. 1.
This will delete the post.

