"use client"

import { useEffect } from "react"

import FallbackPage from "@/components/fallback-page"

export default function Error({
  error,
}: {
  error: Error & { digest?: string }
}) {
  useEffect(() => {
    console.error(error)
  }, [error])

  return (
    <FallbackPage status={500} title={error.name} description={error.message} />
  )
}
