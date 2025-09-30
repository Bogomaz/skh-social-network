package ru.netology

fun main() {
    val testPost1 = Post(
        id = 1,
        ownerId = 10,
        fromId = 2,
        date = 1759092958,
        text = "Post 1 content.",
        replyOwnerId = 2,
        replyPostId = 5,
        friendsOnly = true,
        comments = Comments(
            count = 3,
            canPost = true,
            groupsCanPost = false
        ),
        likes = Likes(
            count = 2,
            userLikes = true,
            canLike = true,
            canPublish = true
        ),
        reposts = Reposts(
            count = 4,
            userReposted = true
        ),
        views = Views(
            count = 123
        ),
        postType = PostType.POST,
        isFavorite = true
    )

    val testPost2 = Post(
        id = 2,
        ownerId = 20,
        fromId = 5,
        date = 1759093999,
        text = "Post 2 content.",
        replyOwnerId = 3,
        replyPostId = 7,
        friendsOnly = false,
        comments = Comments(
            count = 0,
            canPost = false,
            groupsCanPost = true
        ),
        likes = Likes(
            count = 0,
            userLikes = false,
            canLike = false,
            canPublish = false
        ),
        reposts = Reposts(
            count = 0,
            userReposted = false
        ),
        views = Views(
            count = 10
        ),
        postType = PostType.COPY,
        isFavorite = false
    )

    val testPost3 = Post(
        id = 3,
        ownerId = 30,
        fromId = 8,
        date = 1759094999,
        text = "Post 3 content.",
        replyOwnerId = 4,
        replyPostId = 9,
        friendsOnly = true,
        comments = Comments(
            count = 1,
            canPost = true,
            groupsCanPost = false
        ),
        likes = Likes(
            count = 5,
            userLikes = true,
            canLike = true,
            canPublish = false
        ),
        reposts = Reposts(
            count = 2,
            userReposted = false
        ),
        views = Views(
            count = 50
        ),
        postType = PostType.REPLY,
        isFavorite = true
    )
    val testPost4 = Post(
        id = 4,
        ownerId = 40,
        fromId = 12,
        date = 1759095999,
        text = "Post 4 content.",
        replyOwnerId = 5,
        replyPostId = 11,
        friendsOnly = false,
        comments = Comments(
            count = 7,
            canPost = false,
            groupsCanPost = true
        ),
        likes = Likes(
            count = 8,
            userLikes = false,
            canLike = true,
            canPublish = true
        ),
        reposts = Reposts(
            count = 3,
            userReposted = true
        ),
        views = Views(
            count = 200
        ),
        postType = PostType.POSTPONE,
        isFavorite = false
    )

    val testPost5 = Post(
        id = 5,
        ownerId = 50,
        fromId = 15,
        date = 1759096999,
        text = "Post 5 content.",
        replyOwnerId = 6,
        replyPostId = 13,
        friendsOnly = true,
        comments = Comments(
            count = 2,
            canPost = true,
            groupsCanPost = false
        ),
        likes = Likes(
            count = 12,
            userLikes = true,
            canLike = true,
            canPublish = false
        ),
        reposts = Reposts(
            count = 1,
            userReposted = false
        ),
        views = Views(
            count = 75
        ),
        postType = PostType.SUGGEST,
        isFavorite = true
    )

    val testPost6 = Post(
        id = 3,
        ownerId = 30,
        fromId = 8,
        date = 1759095011,
        text = "Post 3 content changed.",
        replyOwnerId = 12,
        replyPostId = 41,
        friendsOnly = false,
        comments = Comments(
            count = 1,
            canPost = true,
            groupsCanPost = false
        ),
        likes = Likes(
            count = 5,
            userLikes = true,
            canLike = true,
            canPublish = false
        ),
        reposts = Reposts(
            count = 2,
            userReposted = false
        ),
        views = Views(
            count = 50
        ),
        postType = PostType.REPLY,
        isFavorite = true
    )

    println(WallService.add(testPost1))
    println(WallService.add(testPost2))
    println(WallService.add(testPost3))
    println(WallService.add(testPost4))
    println(WallService.add(testPost5))

    println("Hello, world")


    WallService.update(testPost6);

}