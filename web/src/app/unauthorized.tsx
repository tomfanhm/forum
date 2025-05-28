import FallbackPage from "@/components/fallback-page"

export default function Unauthorized() {
  return (
    <FallbackPage
      status={401}
      title="Unauthorized"
      description="You are not authorized to access this resource."
    />
  )
}
