package com.mecalco.hugo.marvelapplication.base



/**
 * Annotation for VMActivity
 *
 * @param layoutId layout resource id
 *
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class VMActivity(val layoutId: Int)
