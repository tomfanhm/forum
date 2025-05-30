import React from "react"

import { Skeleton } from "./ui/skeleton"

const DashboardLoading: React.FC = () => {
  return (
    <div className="space-y-2">
      <Skeleton className="h-4 w-full" />
      <Skeleton className="h-4 w-full" />
      <Skeleton className="h-4 w-full" />
    </div>
  )
}
export default DashboardLoading
