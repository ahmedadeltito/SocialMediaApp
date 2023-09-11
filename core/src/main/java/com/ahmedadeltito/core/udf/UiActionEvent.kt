package com.ahmedadeltito.core.udf

/**
 * An Action says what we want to do. Actions usually have a verb as a suffix and then a meaningful name, we can think
 * about actions like:
 * - CreatePostAction
 * - DeletePostAction
 * - ReadPostAction
 * - UpdatePostAction
 *
 * They are immutable so they can not be changed once they have been created.
 */
interface UiActionEvent