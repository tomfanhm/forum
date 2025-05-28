import FallbackPage from "@/components/fallback-page"

export default function NotFound() {
  return (
    <FallbackPage
      status={404}
      title="Not Found"
      description="The requested resource was not found."
    />
  )
}
