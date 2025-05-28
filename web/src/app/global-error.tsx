"use client"

import FallbackPage from "@/components/fallback-page"

export default function GlobalError({
  error,
}: {
  error: Error & { digest?: string }
}) {
  return (
    // global-error must include html and body tags
    <html>
      <body>
        <FallbackPage
          status={500}
          title={error.name}
          description={error.message}
        />
      </body>
    </html>
  )
}
