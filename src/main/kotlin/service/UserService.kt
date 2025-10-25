package ru.netology.service

import ru.netology.exception.UserAlreadyExistsException
import ru.netology.exception.UserNonFoundException
import ru.netology.model.User

class UserService {
    val users = mutableListOf<User>()
    var currentUserId = 1

    // Создать пользователя, если его ещё нет.
    fun createUser(
        login: String,
        firstName: String = "",
        lastName: String = "",
        middleName: String = ""
    ): Boolean {
        return if (users.any() { it.login == login }) {
            throw UserAlreadyExistsException("User $login already exists")
        } else {
            users.add(
                User(
                    id = currentUserId++,
                    firstName = firstName,
                    lastName = lastName,
                    middleName = middleName,
                    login = login
                )
            )
            true
        }
    }

    // Найти пользователя по логину
    fun getUserByLogin(login: String): User {
        return users.firstOrNull { it.login == login && !it.isDeleted }
            ?: throw UserNonFoundException("User $login non exists")
    }

    // Отредактировать пользователя
    fun editUser(
        id: Int,
        newFirstName: String? = null,
        newLastName: String? = null,
        newMiddleName: String? = null
    ): User {
        val index = users.indexOfFirst { it.id == id && !it.isDeleted }
        if (index == -1) throw UserNonFoundException("User $id non exists")
        val oldUser = users[index]
        val updatedUser = oldUser.apply {
            newFirstName?.let { firstName = it }
            newLastName?.let { lastName = it }
            newMiddleName?.let { middleName = it }
        }
        users[index] = updatedUser
        return updatedUser
    }

    // Удалить пользователя, если такой есть
    fun deleteUser(id: Int): Boolean {
        val user = users.firstOrNull { it.id == id && !it.isDeleted }
            ?: throw UserNonFoundException("User $id non exists. Maybe, this user already deleted.")
        user.isDeleted = true
        return true
    }

    // Восстановить пользователя по логину
    fun restoreUser(login: String): User {
        val user = users.firstOrNull() { it.login == login && it.isDeleted }
            ?: throw UserNonFoundException("User $login non exists, or already resotred.")
        user.isDeleted = false
        return user
    }
}
