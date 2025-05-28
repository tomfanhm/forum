import FallbackPage from "@/components/fallback-page"

export default function Forbidden() {
  return (
    <FallbackPage
      status={403}
      title="Forbidden"
      description="You are not authorized to access this resource."
    />
  )
}
