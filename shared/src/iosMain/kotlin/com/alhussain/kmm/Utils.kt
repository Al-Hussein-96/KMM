package com.alhussain.kmm

import platform.Foundation.NSUUID


actual fun randomUUID(): String = NSUUID().UUIDString()